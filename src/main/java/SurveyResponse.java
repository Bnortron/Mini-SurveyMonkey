import jakarta.persistence.*;

@Entity
public class SurveyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public SurveyResponse(Long id) {
        this.id = id;
    }

    public SurveyResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
