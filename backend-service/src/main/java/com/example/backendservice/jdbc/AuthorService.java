package com.example.backendservice.jdbc;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
@ApplicationScoped
public class AuthorService {
    private Connection connect() throws SQLException {
        try {
            // With the URL connection Glassfish do not find the Driver
            // return DriverManager.getConnection(url, user, password);
            Connection conn = null;
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/samples");
            conn = ds.getConnection();
            return conn;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insert(AuthorData author) throws SQLException {
        String SQL = "insert into authors(firstname, lastname, bio) values (?,?,?)";
        try(Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, author.getFirstname());
            stmt.setString(2, author.getLastname());
            stmt.setString(3, author.getBio());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows > 0) {
                try(ResultSet rs = stmt.getGeneratedKeys()) {
                    if(rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        }
        throw new RuntimeException("Insert Author failed");
    }

    public List<AuthorData> findAll() throws SQLException {
        String SQL = "select * from authors";
        try(Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            List<AuthorData> authors = new ArrayList<>();
            while (rs.next()) {
                authors.add(getRecord(rs));
            }
            return authors;
        }
    }

    public AuthorData findById(int id) throws NotFound, SQLException {
        String SQL = "select * from authors where id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) throw new NotFound("Author with id " + id);
            return getRecord(rs);
        }
    }

    private AuthorData getRecord(ResultSet rs) throws SQLException {
        return new AuthorData(
                rs.getLong("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("bio")
        );
    }

    @PostConstruct
    void initDB() {
        log.info("Initialize authors table");
        var sqlStmt = """
                drop table if exists authors;
                create table authors (
                    id serial primary key, 
                    firstname varchar(100) not null, 
                    lastname varchar(100) not null, 
                    bio text);
                insert into authors (firstname, lastname) values ('Hans', 'Muster');
                insert into authors (firstname, lastname) values ('Thomas', 'Mann');
                """.strip();
        try(var conn = connect(); var stmt = conn.prepareStatement(sqlStmt)) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
