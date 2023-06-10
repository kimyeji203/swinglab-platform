package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZoneBookHist is a Querydsl query type for ZoneBookHist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QZoneBookHist extends EntityPathBase<ZoneBookHist> {

    private static final long serialVersionUID = -708258628L;

    public static final QZoneBookHist zoneBookHist = new QZoneBookHist("zoneBookHist");

    public final com.dailystudy.swinglab.service.framework.core.gen.entity.QZoneBookHistCore _super = new com.dailystudy.swinglab.service.framework.core.gen.entity.QZoneBookHistCore(this);

    //inherited
    public final BooleanPath autoBookCnclYn = _super.autoBookCnclYn;

    //inherited
    public final BooleanPath bookCnclYn = _super.bookCnclYn;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> bookEdDt = _super.bookEdDt;

    //inherited
    public final NumberPath<Long> bookId = _super.bookId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> bookStDt = _super.bookStDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    //inherited
    public final NumberPath<Long> userId = _super.userId;

    //inherited
    public final NumberPath<Long> zoneId = _super.zoneId;

    public QZoneBookHist(String variable) {
        super(ZoneBookHist.class, forVariable(variable));
    }

    public QZoneBookHist(Path<? extends ZoneBookHist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZoneBookHist(PathMetadata metadata) {
        super(ZoneBookHist.class, metadata);
    }

}

