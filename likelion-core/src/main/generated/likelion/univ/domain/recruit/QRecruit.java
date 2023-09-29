package likelion.univ.domain.recruit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruit is a Querydsl query type for Recruit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruit extends EntityPathBase<Recruit> {

    private static final long serialVersionUID = 1582838129L;

    public static final QRecruit recruit = new QRecruit("recruit");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public QRecruit(String variable) {
        super(Recruit.class, forVariable(variable));
    }

    public QRecruit(Path<? extends Recruit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruit(PathMetadata metadata) {
        super(Recruit.class, metadata);
    }

}
