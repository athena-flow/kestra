package org.kestra.core.models.conditions.types;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.kestra.core.models.executions.Execution;
import org.kestra.core.models.flows.Flow;
import org.kestra.core.utils.TestsUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class FlowConditionTest {
    @Test
    void valid() {
        Flow flow = TestsUtils.mockFlow();
        Execution execution = TestsUtils.mockExecution(flow, ImmutableMap.of());

        FlowCondition build = FlowCondition.builder()
            .namespace(flow.getNamespace())
            .flowId(flow.getId())
            .build();

        boolean test = build.test(flow, execution);

        assertThat(test, is(true));
    }

    @Test
    void notValid() {
        Flow flow = TestsUtils.mockFlow();
        Execution execution = TestsUtils.mockExecution(flow, ImmutableMap.of());

        FlowCondition build = FlowCondition.builder()
            .namespace(flow.getNamespace() + "a")
            .flowId(flow.getId())
            .build();

        boolean test = build.test(flow, execution);

        assertThat(test, is(false));
    }
}
