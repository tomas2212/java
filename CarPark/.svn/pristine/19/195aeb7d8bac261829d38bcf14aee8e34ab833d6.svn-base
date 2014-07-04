package cz.muni.fi.pa165.vozovypark.DTO;

/**
 *
 * @author Lukas Maticky
 */
public class EmployeeDTO {
    private Long id;
    private String name;
    private String position;
    private Boolean approve;
    private String address;
    private CompanyLevelDTO companyLevel;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public CompanyLevelDTO getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(CompanyLevelDTO companyLevel) {
        this.companyLevel = companyLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeDTO other = (EmployeeDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }   
}