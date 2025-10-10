package com.github.syun9274.hsr_calculator.calculator;

import com.github.syun9274.hsr_calculator.calculator.component.*;
import com.github.syun9274.hsr_calculator.dto.Buff;
import com.github.syun9274.hsr_calculator.dto.character.BasicAttackDto;
import com.github.syun9274.hsr_calculator.dto.CharacterDto;
import com.github.syun9274.hsr_calculator.dto.EnemyDto;
import com.github.syun9274.hsr_calculator.model.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        double damageBoost = 14.4;

        // given - 3. 캐릭터 레벨, 속성
        int characterLevel = 80;
        Element characterElement = Element.PHYSICAL;
        FatePath characterFatePath = FatePath.DESTRUCTION;

        // given - 스킬 계수 정보 추가
        double skillMultiplier = 1;         // 공격력의 100%
        double extraMultiplier = 0.0;       // 조건부 추가 계수 없음
        int extraDamage = 0;

        // given - 4. 적 정보 (약점, 저항, 방어력)
        List<Element> enemyWeakness = Arrays.asList(Element.ICE, Element.WIND, Element.IMAGINARY);
        List<Element> enemyResistance = Arrays.asList(Element.FIRE);
        int enemyDefense = 940;

        // given - 5. 기타 아군 버프 목록
        List<Buff> otherBuffs = Arrays.asList(
                new Buff(BuffType.DAMAGE_BOOST, 95.0),
                new Buff(BuffType.RES_PEN, 24.0)
        );

        // given - 6. 적이 받고 있는 버프
        List<Buff> enemyBuffs = Arrays.asList(
                new Buff(BuffType.DEF_REDUCTION, 8.0),      // 방어력 감소
                new Buff(BuffType.DEF_REDUCTION, 16.0),     // 방어력 감소
                new Buff(BuffType.DAMAGE_TAKEN_INCREASE, 40.0)  // 받는 피해 증가
        );

        // given - 캐릭터 일반 공격 DTO 생성
        BasicAttackDto basicAttack = new BasicAttackDto(
                skillMultiplier,
                extraMultiplier,
                extraDamage);

        // given - 캐릭터 DTO 생성
        CharacterDto character = CharacterDto.builder()
                .name("테스트 캐릭터")
                .level(characterLevel)
                .baseHp(1000)
                .baseAtk(finalStats)
                .baseDef(500)
                .element(characterElement)
                .fatePath(characterFatePath)
                .scalingAttribute(StatType.ATK)
                .basicAttack(basicAttack)
                .skill(null)
                .ultimate(null)
                .build();

        // given - 적 DTO 생성
        EnemyDto enemy = EnemyDto.builder()
                .name("테스트 적")
                .level(74)
                .baseHp(10000)
                .baseDef(enemyDefense)
                .weaknessElements(enemyWeakness)
                .resistElements(enemyResistance)
                .isBroken(false)
                .build();

        // given - 캐릭터 버프
        List<Buff> charBuffs = new ArrayList<>();
        charBuffs.add(new Buff(BuffType.DAMAGE_BOOST, damageBoost));
        charBuffs.add(new Buff(BuffType.BASIC_ATTACK_DAMAGE_BOOST, 0.0));
        charBuffs.addAll(otherBuffs); // 기타 버프 추가

        boolean isBroken = false; // 약점 격파 상태 아님

        // when
        Map<DamageType, Integer> result = damageCalculator.calculateFinalDamage(
                character, enemy, charBuffs, enemyBuffs, isBroken);

        // then
        assertNotNull(result);
        assertTrue(result.containsKey(DamageType.BASIC_NORMAL));
        int finalDamage = result.get(DamageType.BASIC_NORMAL);

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