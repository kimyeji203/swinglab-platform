package com.dailystudy.swinglab.service.framework.core.gen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZoneBookHistCore is a Querydsl query type for ZoneBookHistCore
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QZoneBookHistCore extends EntityPathBase<ZoneBookHistCore> {

    private static final long serialVersionUID = -1458609461L;

    public static final QZoneBookHistCore zoneBookHistCore = new QZoneBookHistCore("zoneBookHistCore");

    public final com.dailystudy.swinglab.service.framework.core.QBaseEntity _super = new com.dailystudy.swinglab.service.framework.core.QBaseEntity(this);

    public final BooleanPath autoBookCnclYn = createBoolean("autoBookCnclYn");

    public final BooleanPath bookCnclYn = createBoolean("bookCnclYn");

    public final DateTimePath<java.time.LocalDateTime> bookEdDt = createDateTime("bookEdDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> bookStDt = createDateTime("bookStDt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Long> zoneId = createNumber("zoneId", Long.class);

    public QZoneBookHistCore(String variable) {
        super(ZoneBookHistCore.class, forVariable(variable));
    }

    public QZoneBookHistCore(Path<? extends ZoneBookHistCore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZoneBookHistCore(PathMetadata metadata) {
        super(ZoneBookHistCore.class, metadata);
    }

}

