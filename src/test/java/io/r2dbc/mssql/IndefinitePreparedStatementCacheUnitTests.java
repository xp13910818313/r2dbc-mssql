/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.r2dbc.mssql;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link IndefinitePreparedStatementCache}.
 *
 * @author Mark Paluch
 */
class IndefinitePreparedStatementCacheUnitTests {

    @Test
    void shouldReuseCachedStatements() {

        AtomicInteger parseCounter = new AtomicInteger();
        IndefinitePreparedStatementCache cache = new IndefinitePreparedStatementCache();

        cache.getParsedSql("statement", s -> {
            parseCounter.incrementAndGet();
            return Optional.of(s);
        });

        cache.getParsedSql("statement", s -> {
            parseCounter.incrementAndGet();
            return Optional.of(s);
        });

        assertThat(parseCounter).hasValue(1);
    }

}
