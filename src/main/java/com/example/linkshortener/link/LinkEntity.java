package com.example.linkshortener.link;

import com.example.linkshortener.link.api.LinkDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class LinkEntity {

    @Id
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String targetUrl;
    private LocalDate expirationDate;
    private int visits;

    static LinkEntity fromDto(LinkDto link) {
        return new LinkEntity(
                link.id(),
                link.email(),
                link.targetUrl(),
                link.expirationDate(),
                link.visits()
        );
    }

    void setVisits(final int visits) {
        this.visits = visits;
    }

    int getVisits() {
        return visits;
    }


}
