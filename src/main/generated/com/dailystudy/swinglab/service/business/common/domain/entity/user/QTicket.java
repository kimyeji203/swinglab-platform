package com.dailystudy.swinglab.service.business.common.domain.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTicket is a Querydsl query type for Ticket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTicket extends EntityPathBase<Ticket> {

    private static final long serialVersionUID = -602345198L;

    public static final QTicket ticket = new QTicket("ticket");

    public final com.dailystudy.swinglab.service.framework.core.gen.entity.QTicketCore _super = new com.dailystudy.swinglab.service.framework.core.gen.entity.QTicketCore(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DatePath<java.time.LocalDate> svcEdDay = _super.svcEdDay;

    //inherited
    public final DatePath<java.time.LocalDate> svcRegDay = _super.svcRegDay;

    //inherited
    public final DatePath<java.time.LocalDate> svcStDay = _super.svcStDay;

    //inherited
    public final NumberPath<Long> ticketId = _super.ticketId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    //inherited
    public final NumberPath<Long> userId = _super.userId;

    public QTicket(String variable) {
        super(Ticket.class, forVariable(variable));
    }

    public QTicket(Path<? extends Ticket> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTicket(PathMetadata metadata) {
        super(Ticket.class, metadata);
    }

}

