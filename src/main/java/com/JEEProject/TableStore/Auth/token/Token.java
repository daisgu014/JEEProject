package com.JEEProject.TableStore.Auth.token;

import com.JEEProject.TableStore.Auth.user.User;
import com.JEEProject.TableStore.Model.Account;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "token")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

}
