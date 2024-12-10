package shopify.demo.model.base;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


import jakarta.persistence.Column;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseEntity {
    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name = "created_dby")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by" )
    @LastModifiedBy
    private String modifiedBy;
}