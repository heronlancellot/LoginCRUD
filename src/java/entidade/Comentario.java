package entidade;

import java.sql.Date;

public class Comentario {

    private int id = 0;
    private String comentario;
    private Date data;
    private int idusuario;
    private int idcategoria;
    private String nomeususario;
    private String nomeCategoria;

    public Comentario() {

    }

    public Comentario(int id, String comentario, Date data, int idusuario, int idcategoria) {
        this.id = id;
        this.comentario = comentario;
        this.data = data;
        this.idusuario = idusuario;
        this.idcategoria = idcategoria;
        this.nomeususario = "";
        this.nomeCategoria = "";
    }
    
public Comentario(int id, String comentario) {
    this.id = id;
    this.comentario = comentario;
}

public Comentario(int idUsuario, String comentario, Date data) {
    this.id = idUsuario;
    this.comentario = comentario;
    this.data = data;
}

    public Comentario(int id, String comentario, Date data, int idcategoria) {
        this.id = id;
        this.comentario = comentario;
        this.data = data;
        this.idcategoria = idcategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
        public String getNomeususario() {
        return nomeususario;
    }

    public void setNomeususario(String nomeususario) {
        this.nomeususario = nomeususario;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
    
    

    
}
