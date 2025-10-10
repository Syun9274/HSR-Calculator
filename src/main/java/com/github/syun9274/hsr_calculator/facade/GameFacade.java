package com.github.syun9274.hsr_calculator.facade;

import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.dto.CharacterDto;
import com.github.syun9274.hsr_calculator.dto.EnemyDto;
import com.github.syun9274.hsr_calculator.dto.request.BuffListRequest;
import com.github.syun9274.hsr_calculator.dto.request.ManualCharacterConfigRequest;
import com.github.syun9274.hsr_calculator.dto.request.ManualEnemyConfigRequest;
import com.github.syun9274.hsr_calculator.dto.response.DamageResult;
import com.github.syun9274.hsr_calculator.model.enums.DamageType;
import com.github.syun9274.hsr_calculator.service.CharacterService;
import com.github.syun9274.hsr_calculator.service.DamageCalculationService;
import com.github.syun9274.hsr_calculator.service.EnemyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class GameFacade {

    private final CharacterService characterService;
    private final DamageCalculationService damageCalculationService;
    private final EnemyService enemyService;

    /**
     * 수동 설정된 캐릭터와 적 정보를 바탕으로 데미지를 계산합니다.
     *
     * @param characterReq     캐릭터 수동 설정 정보 (스탯, 스킬 등)
     * @param enemyReq         적 수동 설정 정보 (레벨, 방어력, 약점 등)
     * @param charBuffListReq  캐릭터에게 적용할 버프 목록
     * @param enemyBuffListReq 적에게 적용할 디버프 목록
     * @return 최종 계산된 데미지 값
     */
    public DamageResult calculateDamageManual(
            ManualCharacterConfigRequest characterReq,
            ManualEnemyConfigRequest enemyReq,
            BuffListRequest charBuffListReq,
            BuffListRequest enemyBuffListReq
    ) {
        // characterDto 변환
        CharacterDto characterDto = characterService.configureCharacterManually(characterReq);

        // enemyDto 변환
        EnemyDto enemyDto = enemyService.configureEnemyManually(enemyReq);

        // BuffList로 변환
        List<Buff> charBuffs = charBuffListReq.toBuffList();
        List<Buff> enemyBuffs = enemyBuffListReq.toBuffList();

        Map<DamageType, Integer> finalDamage = damageCalculationService.calculateDamage(
                characterDto, enemyDto, charBuffs, enemyBuffs, enemyDto.isBroken()
        );

        return DamageResult.from(finalDamage);
    }
}
