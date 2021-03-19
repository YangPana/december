package com.yarns.december.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.yarns.december.support.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * @author Yarns
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtils.formatFullTime(LocalDateTime.now(), DateUtils.FULL_TIME_SPLIT_PATTERN)
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
    }
}
