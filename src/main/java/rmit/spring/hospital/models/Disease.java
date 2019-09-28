package rmit.spring.hospital.models;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "diseases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "chapter_code")
//    private String chapterCode;
//
//    @Column(name = "chapter_name")
//    private String chapterName;
//
//    @Column(name = "group_code")
//    private String groupCode;
//
//    @Column(name = "group_name")
//    private String groupName;
//
//    @Column(name = "type_code")
//    private String typeCode;
//
//    @Column(name = "type_name")
//    private String typeName;
//
//    @Column(name = "disease_code")
//    private String diseaseCode;

    @Column(name = "disease_name")
    private String diseaseName;

    public Disease(String diseaseName){
        this.diseaseName = diseaseName;
    }

}
