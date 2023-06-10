package com.dailystudy.swinglab.service.business.common.domain.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 415054673L;

    public static final QUser user = new QUser("user");

    public final com.dailystudy.swinglab.service.framework.core.gen.entity.QUserCore _super = new com.dailystudy.swinglab.service.framework.core.gen.entity.QUserCore(this);

    //inherited
    public final BooleanPath delYn = _super.delYn;

    //inherited
    public final StringPath loginId = _super.loginId;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath nickNm = _super.nickNm;

    //inherited
    public final StringPath pwd = _super.pwd;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> signupDt = _super.signupDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    //inherited
    public final NumberPath<Long> userId = _super.userId;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

