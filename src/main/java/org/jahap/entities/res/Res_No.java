/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.entities.res;

import org.jahap.entities.acc.BILLNO_ie;
import org.jahap.entities.acc.Bill;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


/**
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
@Entity
@Table(name = "RES_NO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResNo.findAll", query = "SELECT b FROM Res_No b"),
    @NamedQuery(name = "ResNo.findByBillno", query = "SELECT b FROM Res_No b WHERE b.resno = :resno")})
public class Res_No implements Serializable, RESNO_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESNO")
    @TableGenerator( name = "RES_gen", table = "SEQ_STORE", pkColumnName = "TABLE_NAME", pkColumnValue = "RES", valueColumnName = "VALUE" , allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "RES_gen" )
    private Long resno;
    @OneToOne(fetch=FetchType.LAZY, mappedBy="resnointern")
    private Res res;


    public Res_No() {
    }


    public Res_No(Long resno) {
        this.resno = resno;
    }

    @Override
    public Res getRes() {
        return res;
    }

    @Override
    public void setRes(Res ress) {
        this.res = res;
    }

    
    
    
    @Override
    public Long getResno() {
        return resno;
    }

    @Override
    public void setResno(Long resno) {
        this.resno = resno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resno != null ? resno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Res_No)) {
            return false;
        }
        Res_No other = (Res_No) object;
        if ((this.resno == null && other.resno != null) || (this.resno != null && !this.resno.equals(other.resno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.BillNo[ resno=" + resno + " ]";
    }
    
}
