package com.dailystudy.swinglab.service.framework.core.gen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApiLogCore is a Querydsl query type for ApiLogCore
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QApiLogCore extends EntityPathBase<ApiLogCore> {

    private static final long serialVersionUID = -862426530L;

    public static final QApiLogCore apiLogCore = new QApiLogCore("apiLogCore");

    public final com.dailystudy.swinglab.service.framework.core.QBaseEntity _super = new com.dailystudy.swinglab.service.framework.core.QBaseEntity(this);

    public final StringPath api = createString("api");

    public final StringPath clintIp = createString("clintIp");

    public final NumberPath<Integer> httpSttusCd = createNumber("httpSttusCd", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public final StringPath reqBody = createString("reqBody");

    public final DateTimePath<java.util.Date> reqDt = createDateTime("reqDt", java.util.Date.class);

    public final StringPath resBody = createString("resBody");

    public final NumberPath<Long> resBodyLen = createNumber("resBodyLen", Long.class);

    public final StringPath resCd = createString("resCd");

    public final DateTimePath<java.util.Date> resDt = createDateTime("resDt", java.util.Date.class);

    public final BooleanPath resYn = createBoolean("resYn");

    public final StringPath txId = createString("txId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public QApiLogCore(String variable) {
        super(ApiLogCore.class, forVariable(variable));
    }

    public QApiLogCore(Path<? extends ApiLogCore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApiLogCore(PathMetadata metadata) {
        super(ApiLogCore.class, metadata);
    }

}

