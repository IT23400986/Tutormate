package com.group13.tutormate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String filename;

    private String status; // "approved", "rejected", "pending"

    @Lob
    @Column(length = 1000)
    private byte[] fileUpload;
}

