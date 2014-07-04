package cz.muni.fi.pa165.vozovypark.DTO;

import java.util.Date;

/**
 *
 * @author Tomas Svrcek
 */
public class CarDTO {
    private Long id;
    private String spz;
    private String brand;
    private String model;
    private Date creationYear;
    private Boolean available;
    private CompanyLevelDTO companyLevel;

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CompanyLevelDTO getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(CompanyLevelDTO companyLevel) {
        this.companyLevel = companyLevel;
    }

    public Date getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Date creationYear) {
        this.creationYear = creationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarDTO other = (CarDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    
    
}
