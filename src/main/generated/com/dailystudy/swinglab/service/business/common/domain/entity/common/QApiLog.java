package com.dailystudy.swinglab.service.business.common.domain.entity.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApiLog is a Querydsl query type for ApiLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApiLog extends EntityPathBase<ApiLog> {

    private static final long serialVersionUID = -20162064L;

    public static final QApiLog apiLog = new QApiLog("apiLog");

    public final com.dailystudy.swinglab.service.framework.core.gen.entity.QApiLogCore _super = new com.dailystudy.swinglab.service.framework.core.gen.entity.QApiLogCore(this);

    //inherited
    public final StringPath api = _super.api;

    //inherited
    public final StringPath clintIp = _super.clintIp;

    //inherited
    public final NumberPath<Integer> httpSttusCd = _super.httpSttusCd;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath reqBody = _super.reqBody;

    //inherited
    public final DateTimePath<java.util.Date> reqDt = _super.reqDt;

    //inherited
    public final StringPath resBody = _super.resBody;

    //inherited
    public final NumberPath<Long> resBodyLen = _super.resBodyLen;

    //inherited
    public final StringPath resCd = _super.resCd;

    //inherited
    public final DateTimePath<java.util.Date> resDt = _super.resDt;

    //inherited
    public final BooleanPath resYn = _super.resYn;

    //inherited
    public final StringPath txId = _super.txId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public QApiLog(String variable) {
        super(ApiLog.class, forVariable(variable));
    }

    public QApiLog(Path<? extends ApiLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApiLog(PathMetadata metadata) {
        super(ApiLog.class, metadata);
    }

}

