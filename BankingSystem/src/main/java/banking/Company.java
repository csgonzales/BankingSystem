package banking;

import java.util.Objects;

public class Company extends AccountHolder {
	private String companyName;

	public Company(String companyName, int taxId) {
		super(taxId);
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Company)) return false;
		if (!super.equals(o)) return false;
		Company company = (Company) o;
		return Objects.equals(companyName, company.companyName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), companyName);
	}
}
