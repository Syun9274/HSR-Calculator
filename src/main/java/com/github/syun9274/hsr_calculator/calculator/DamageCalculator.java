package com.github.syun9274.hsr_calculator.calculator;

import com.github.syun9274.hsr_calculator.calculator.component.*;
import com.github.syun9274.hsr_calculator.dto.CharacterDto;
import com.github.syun9274.hsr_calculator.dto.EnemyDto;
import com.github.syun9274.hsr_calculator.exception.CustomException;
import com.github.syun9274.hsr_calculator.exception.ErrorCode;
import com.github.syun9274.hsr_calculator.dto.BuffDto;
import com.github.syun9274.hsr_calculator.model.enums.DamageType;
import com.github.syun9274.hsr_calculator.model.enums.StatType;
import com.github.syun9274.hsr_calculator.util.MathUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class DamageCalculator {

    private final StatCalculator statCalculator;
    private final BaseDmg baseDmg;
    private final CritMultiplier critMultiplier;
    private final DefMultiplier defMultiplier;
    private final DmgMultiplier dmgMultiplier;
    private final DmgTakenMultiplier dmgTakenMultiplier;
    private final ResMultiplier resMultiplier;
    private final UniversalDmgReductionMultiplier universalDmgReductionMultiplier;
    private final WeakenMultiplier weakenMultiplier;

    public Map<DamageType, Integer> calculateOutgoingDmg(CharacterDto character,
                                                         EnemyDto enemy,
                                                         List<BuffDto> charBuffDtos,
                                                         List<BuffDto> enemyBuffDtos,
                                                         boolean isBroken) {
        try {
            double outGoingDamage = 0.0;

            // 공격 타입에 따라 달라지는 변수를 제외한 나머지 계산
            double def = defMultiplier.getDefMultiplier(character, enemy, charBuffDtos, enemyBuffDtos);
            double dmgTaken = dmgTakenMultiplier.getDmgTakenMultiplier(enemyBuffDtos);
            double res = resMultiplier.getResMultiplier(character, enemy, charBuffDtos, enemyBuffDtos);
            double uniDmgRed = universalDmgReductionMultiplier.getUniversalDmgReductionMultiplier(isBroken);
            double weak = weakenMultiplier.getWeakenMultiplier(enemyBuffDtos);

            outGoingDamage = def * dmgTaken * res * uniDmgRed * weak;

            // 캐릭터 스탯에 버프 일괄 적용
            Map<StatType, Double> finalStats = statCalculator.calculateFinalStats(
                    character.baseHp(),
                    character.baseAtk(),
                    character.baseDef(),
                    charBuffDtos);

            // 계산에 사용되는 캐릭터 스탯 추출
            double scalingAttribute = switch (character.scalingAttribute()) {
                case StatType.HP -> finalStats.get(StatType.HP);
                case StatType.DEF -> finalStats.get(StatType.DEF);
                default -> finalStats.get(StatType.ATK);
            };

            double finalDamage = outGoingDamage *
                    baseDmg.getBaseDmg(
                            character.basicAttack().skillMultiplier(),
                            character.basicAttack().extraMultiplier(),
                            scalingAttribute,
                            character.basicAttack().extraDamage()) *
                    dmgMultiplier.getBasicAttackDmgMultiplier(character.element(), charBuffDtos);

            return Map.of(
                    DamageType.BASIC_NORMAL, MathUtil.toGameDamageInt(finalDamage));

        } catch (ArithmeticException e) {
            log.error("Damage calculation overflow: {}", e.getMessage());
            throw new CustomException(ErrorCode.DAMAGE_CALCULATION_OVERFLOW);

        } catch (Exception e) {
            log.error("Damage calculation error: {}", e.getMessage());
            throw new CustomException(ErrorCode.CALCULATION_ERROR);
        }
    }
}
