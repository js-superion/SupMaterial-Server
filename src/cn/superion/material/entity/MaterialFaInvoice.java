package cn.superion.material.entity;

/**
 * MaterialFaInvoiceId entity. @author MyEclipse Persistence Tools
 */

public class MaterialFaInvoice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956169563682536507L;
	private String mainAutoId;
	private String invoiceAutoId;

	// Constructors

	/** default constructor */
	public MaterialFaInvoice() {
	}

	/** full constructor */
	public MaterialFaInvoice(String mainAutoId, String invoiceAutoId) {
		this.mainAutoId = mainAutoId;
		this.invoiceAutoId = invoiceAutoId;
	}

	// Property accessors

	public String getMainAutoId() {
		return this.mainAutoId;
	}

	public void setMainAutoId(String mainAutoId) {
		this.mainAutoId = mainAutoId;
	}

	public String getInvoiceAutoId() {
		return this.invoiceAutoId;
	}

	public void setInvoiceAutoId(String invoiceAutoId) {
		this.invoiceAutoId = invoiceAutoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaterialFaInvoice))
			return false;
		MaterialFaInvoice castOther = (MaterialFaInvoice) other;

		return ((this.getMainAutoId() == castOther.getMainAutoId()) || (this
				.getMainAutoId() != null
				&& castOther.getMainAutoId() != null && this.getMainAutoId()
				.equals(castOther.getMainAutoId())))
				&& ((this.getInvoiceAutoId() == castOther.getInvoiceAutoId()) || (this
						.getInvoiceAutoId() != null
						&& castOther.getInvoiceAutoId() != null && this
						.getInvoiceAutoId()
						.equals(castOther.getInvoiceAutoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMainAutoId() == null ? 0 : this.getMainAutoId()
						.hashCode());
		result = 37
				* result
				+ (getInvoiceAutoId() == null ? 0 : this.getInvoiceAutoId()
						.hashCode());
		return result;
	}

}