package com.dailystudy.swinglab.service.framework.core.gen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserCore is a Querydsl query type for UserCore
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QUserCore extends EntityPathBase<UserCore> {

    private static final long serialVersionUID = -1740514273L;

    public static final QUserCore userCore = new QUserCore("userCore");

    public final com.dailystudy.swinglab.service.framework.core.QBaseEntity _super = new com.dailystudy.swinglab.service.framework.core.QBaseEntity(this);

    public final BooleanPath delYn = createBoolean("delYn");

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final StringPath nickNm = createString("nickNm");

    public final StringPath pwd = createString("pwd");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public final DateTimePath<java.time.LocalDateTime> signupDt = createDateTime("signupDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> ticketId = createNumber("ticketId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserCore(String variable) {
        super(UserCore.class, forVariable(variable));
    }

    public QUserCore(Path<? extends UserCore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserCore(PathMetadata metadata) {
        super(UserCore.class, metadata);
    }

}

