create table performance_schema.performance_timers
(
    TIMER_NAME       enum ('CYCLE', 'NANOSECOND', 'MICROSECOND', 'MILLISECOND', 'THREAD_CPU') not null,
    TIMER_FREQUENCY  bigint                                                                   null,
    TIMER_RESOLUTION bigint                                                                   null,
    TIMER_OVERHEAD   bigint                                                                   null
)
    engine = PERFORMANCE_SCHEMA;

