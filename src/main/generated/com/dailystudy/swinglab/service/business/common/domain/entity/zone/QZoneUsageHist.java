package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZoneUsageHist is a Querydsl query type for ZoneUsageHist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QZoneUsageHist extends EntityPathBase<ZoneUsageHist> {

    private static final long serialVersionUID = -1710647438L;

    public static final QZoneUsageHist zoneUsageHist = new QZoneUsageHist("zoneUsageHist");

    public final com.dailystudy.swinglab.service.framework.core.gen.entity.QZoneUsageHistCore _super = new com.dailystudy.swinglab.service.framework.core.gen.entity.QZoneUsageHistCore(this);

    //inherited
    public final BooleanPath autoChkOutYn = _super.autoChkOutYn;

    //inherited
    public final NumberPath<Long> bookId = _super.bookId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chkInDt = _super.chkInDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chkOutDt = _super.chkOutDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public QZoneUsageHist(String variable) {
        super(ZoneUsageHist.class, forVariable(variable));
    }

    public QZoneUsageHist(Path<? extends ZoneUsageHist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZoneUsageHist(PathMetadata metadata) {
        super(ZoneUsageHist.class, metadata);
    }

}

