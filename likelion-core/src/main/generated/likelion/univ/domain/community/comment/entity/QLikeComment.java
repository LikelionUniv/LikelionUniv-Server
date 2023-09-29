package likelion.univ.domain.community.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeComment is a Querydsl query type for LikeComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeComment extends EntityPathBase<LikeComment> {

    private static final long serialVersionUID = -1813527282L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeComment likeComment = new QLikeComment("likeComment");

    public final likelion.univ.domain.common.QBaseTimeEntity _super = new likelion.univ.domain.common.QBaseTimeEntity(this);

    public final QComment comment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final likelion.univ.domain.user.entity.QUser user;

    public QLikeComment(String variable) {
        this(LikeComment.class, forVariable(variable), INITS);
    }

    public QLikeComment(Path<? extends LikeComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeComment(PathMetadata metadata, PathInits inits) {
        this(LikeComment.class, metadata, inits);
    }

    public QLikeComment(Class<? extends LikeComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
        this.user = inits.isInitialized("user") ? new likelion.univ.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

