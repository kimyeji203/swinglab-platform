package com.dailystudy.swinglab.service.framework.core.gen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZoneCore is a Querydsl query type for ZoneCore
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QZoneCore extends EntityPathBase<ZoneCore> {

    private static final long serialVersionUID = -625788032L;

    public static final QZoneCore zoneCore = new QZoneCore("zoneCore");

    public final com.dailystudy.swinglab.service.framework.core.QBaseEntity _super = new com.dailystudy.swinglab.service.framework.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public final NumberPath<Long> zoneId = createNumber("zoneId", Long.class);

    public final StringPath zoneNm = createString("zoneNm");

    public QZoneCore(String variable) {
        super(ZoneCore.class, forVariable(variable));
    }

    public QZoneCore(Path<? extends ZoneCore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZoneCore(PathMetadata metadata) {
        super(ZoneCore.class, metadata);
    }

}

