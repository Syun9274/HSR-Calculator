package com.github.syun9274.hsr_damage_calculator.calculator;

import com.github.syun9274.hsr_damage_calculator.calculator.component.*;
import com.github.syun9274.hsr_damage_calculator.dto.BuffDto;
import com.github.syun9274.hsr_damage_calculator.model.Character;
import com.github.syun9274.hsr_damage_calculator.model.CharacterAbility;
import com.github.syun9274.hsr_damage_calculator.model.Enemy;
import com.github.syun9274.hsr_damage_calculator.model.enums.BuffType;
import com.github.syun9274.hsr_damage_calculator.model.enums.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.github.syun9274.hsr_damage_calculator.util.MathUtil.percentToDecimal;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DamageCalculatorTest {

    private DamageCalculator damageCalculator;

    @BeforeEach
    void setUp() {
        // 실제 객체들로 생성 (의존성 주입 없이)
        StatCalculator statCalculator = new StatCalculator();
        BaseDmg baseDmg = new BaseDmg();
        CritMultiplier critMultiplier = new CritMultiplier();
        DefMultiplier defMultiplier = new DefMultiplier();
        ResMultiplier resMultiplier = new ResMultiplier();
        DmgMultiplier dmgMultiplier = new DmgMultiplier();
        DmgTakenMultiplier dmgTakenMultiplier = new DmgTakenMultiplier();
        UniversalDmgReductionMultiplier universalDmgReductionMultiplier = new UniversalDmgReductionMultiplier();
        WeakenMultiplier weakenMultiplier = new WeakenMultiplier();

        damageCalculator = new DamageCalculator(
                statCalculator, baseDmg, critMultiplier, defMultiplier,
                dmgMultiplier, dmgTakenMultiplier, resMultiplier,
                universalDmgReductionMultiplier, weakenMultiplier
        );
    }

    @Test
    @DisplayName("데미지 계산 공식 검증 - 클라라 트리비 선데이 사이퍼")
    void calculateDamage_ManualInput() {
        // given - 1. 최종 스탯 (모든 버프 적용 완료)
        int baseStats = 1319;
        int buffStats = 1002;
        int finalStats = baseStats + buffStats;

        // given - 2. 캐릭터 자체 가하는 피해 증가 버프
        double damageBoost = percentToDecimal(14.4);

        // given - 3. 캐릭터 레벨, 속성
        int characterLevel = 80;
        Element characterElement = Element.PHYSICAL;

        // given - 스킬 계수 정보 추가
        double skillMultiplier = 1;         // 공격력의 100%
        double extraMultiplier = 0.0;       // 조건부 추가 계수 없음
        int extraDamage = 0;

        // given - 4. 적 정보 (약점, 저항, 방어력)
        List<Element> enemyWeakness = Arrays.asList(Element.ICE, Element.WIND, Element.IMAGINARY);
        List<Element> enemyResistance = Arrays.asList(Element.FIRE);
        int enemyDefense = 940;

        // given - 5. 기타 아군 버프 목록
        List<BuffDto> otherBuffDtos = Arrays.asList(
                new BuffDto(BuffType.DAMAGE_BOOST, percentToDecimal(95)),
                new BuffDto(BuffType.RES_PEN, percentToDecimal(24))
        );

        // given - 6. 적이 받고 있는 버프
        List<BuffDto> enemyBuffDtos = Arrays.asList(
                new BuffDto(BuffType.DEF_REDUCTION, percentToDecimal(8)),      // 방어력 감소
                new BuffDto(BuffType.DEF_REDUCTION, percentToDecimal(16)),     // 방어력 감소
                new BuffDto(BuffType.DAMAGE_TAKEN_INCREASE, percentToDecimal(40))  // 받는 피해 증가
        );

        // given - 캐릭터 객체 생성
        Character character = new Character() {
            @Override
            public CharacterAbility getBasicAttack() {
                CharacterAbility basicAttack = new CharacterAbility();
                basicAttack.setSkillMultiplier(skillMultiplier);
                basicAttack.setExtraMultiplier(extraMultiplier);
                basicAttack.setExtraDamage(extraDamage);
                return basicAttack;
            }
        };
        character.setLevel(characterLevel);
        character.setElement(characterElement);
        character.setBaseAtk(finalStats);
        character.setScalingAttribute("atk");

        // given - 적 객체 생성
        Enemy enemy = new Enemy();
        enemy.setLevel(74);
        enemy.setBaseDef(enemyDefense);
        enemy.setWeaknessElements(enemyWeakness);
        enemy.setResistElements(enemyResistance);

        // given - 캐릭터 버프
        List<BuffDto> charBuffDtos = new ArrayList<>();
        charBuffDtos.add(new BuffDto(BuffType.DAMAGE_BOOST, damageBoost));
        charBuffDtos.add(new BuffDto(BuffType.BASIC_ATTACK_DAMAGE_BOOST, percentToDecimal(0)));
        charBuffDtos.addAll(otherBuffDtos); // 기타 버프 추가

        boolean isBroken = false; // 약점 격파 상태 아님

        // when
        Map<String, Integer> result = damageCalculator.calculateOutgoingDmg(
                character, enemy, charBuffDtos, enemyBuffDtos, isBroken);

        // then
        assertNotNull(result);
        assertTrue(result.containsKey("Final Damage"));
        int finalDamage = result.get("Final Damage");

        // 수동 계산, 예상 값과 비교 (소수점 처리 고려)
        int expectedMinDamage = 3714; // 예상 최소 데미지
        int expectedMaxDamage = 3716; // 예상 최대 데미지

        assertTrue(finalDamage > 0, "데미지는 0보다 커야 함");
        assertTrue(finalDamage >= expectedMinDamage && finalDamage <= expectedMaxDamage,
                String.format("Expected damage between %d - %d, but was: %d",
                        expectedMinDamage, expectedMaxDamage, finalDamage));
        // 계산 결과 3715
        // 게임 내 결과 3716 (소수점 처리 방식의 차이로 허용 가능 범위)

        // 디버깅용 출력
        System.out.println("=== 데미지 계산 결과 ===");
        System.out.println("최종 공격력: " + finalStats);
        System.out.println("캐릭터 속성: " + characterElement);
        System.out.println("적 약점: " + enemyWeakness);
        System.out.println("적 방어력: " + enemyDefense);
        System.out.println("계산된 데미지: " + finalDamage);
    }
}