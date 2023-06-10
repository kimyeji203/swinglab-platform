package com.dailystudy.swinglab.service.framework.core.gen.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTicketCore is a Querydsl query type for TicketCore
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QTicketCore extends EntityPathBase<TicketCore> {

    private static final long serialVersionUID = -932339296L;

    public static final QTicketCore ticketCore = new QTicketCore("ticketCore");

    public final com.dailystudy.swinglab.service.framework.core.QBaseEntity _super = new com.dailystudy.swinglab.service.framework.core.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public final DatePath<java.time.LocalDate> svcEdDay = createDate("svcEdDay", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> svcRegDay = createDate("svcRegDay", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> svcStDay = createDate("svcStDay", java.time.LocalDate.class);

    public final NumberPath<Long> ticketId = createNumber("ticketId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final BooleanPath useYn = createBoolean("useYn");

    public QTicketCore(String variable) {
        super(TicketCore.class, forVariable(variable));
    }

    public QTicketCore(Path<? extends TicketCore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTicketCore(PathMetadata metadata) {
        super(TicketCore.class, metadata);
    }

}

