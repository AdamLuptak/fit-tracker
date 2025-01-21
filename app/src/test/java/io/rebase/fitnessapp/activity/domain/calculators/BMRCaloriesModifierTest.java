package io.rebase.fitnessapp.activity.domain.calculators;

import static org.assertj.core.api.Assertions.*;

import io.rebase.fitnessapp.calorie.domain.calculators.BMRCaloriesModifier;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

class BMRCaloriesModifierTest {

  @Test
  void shouldCalculateCorrectBMR() {
    var modifier = new BMRCaloriesModifier(1000);
    var actualBMR = modifier.modify(70, 175, 25);
    assertThat(actualBMR).isCloseTo(191.52777777777777, Offset.offset(0.0001));
  }
}
