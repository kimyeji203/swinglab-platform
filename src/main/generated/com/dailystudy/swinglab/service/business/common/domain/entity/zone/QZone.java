package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZone is a Querydsl query type for Zone
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QZone extends EntityPathBase<Zone> {

    private static final long serialVersionUID = 611975153L;

    public static final QZone zone = new QZone("zone");

    public final com.dailystudy.swinglab.service.framework.core.gen.entity.QZoneCore _super = new com.dailystudy.swinglab.service.framework.core.gen.entity.QZoneCore(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    //inherited
    public final NumberPath<Long> zoneId = _super.zoneId;

    //inherited
    public final StringPath zoneNm = _super.zoneNm;

    public QZone(String variable) {
        super(Zone.class, forVariable(variable));
    }

    public QZone(Path<? extends Zone> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZone(PathMetadata metadata) {
        super(Zone.class, metadata);
    }

}

