package com.dailystudy.swinglab.service.framework.core.gen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZoneUsageHistCore is a Querydsl query type for ZoneUsageHistCore
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QZoneUsageHistCore extends EntityPathBase<ZoneUsageHistCore> {

    private static final long serialVersionUID = -1386572831L;

    public static final QZoneUsageHistCore zoneUsageHistCore = new QZoneUsageHistCore("zoneUsageHistCore");

    public final com.dailystudy.swinglab.service.framework.core.QBaseEntity _super = new com.dailystudy.swinglab.service.framework.core.QBaseEntity(this);

    public final BooleanPath autoChkOutYn = createBoolean("autoChkOutYn");

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> chkInDt = createDateTime("chkInDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> chkOutDt = createDateTime("chkOutDt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public QZoneUsageHistCore(String variable) {
        super(ZoneUsageHistCore.class, forVariable(variable));
    }

    public QZoneUsageHistCore(Path<? extends ZoneUsageHistCore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZoneUsageHistCore(PathMetadata metadata) {
        super(ZoneUsageHistCore.class, metadata);
    }

}

