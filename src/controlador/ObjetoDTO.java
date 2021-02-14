package controlador;

import java.util.Date;

public class ObjetoDTO {
	
	
	

	private String codigo = "";
	private String nombreEquipo= "";
	private String indGrupo= "";
	private String periodoCalibracion= "";
	private String calibradorExterno= "";
	private String fabricante= "";
	private String modelo= "";
	private Date fechaAlta;
	private Date fechaBaja;
	private String codigoUbicacion= "";
	private Date fechaUltimaCalibracion;
	private Date fechaProximaCalibracion;
	private String datosTecnicosEquipo= "";
	private String rango= "";
	private String errorActual= "";
	private String errorMaxAdmisibre= "";
	private boolean equipoCalibrado;
	private boolean iso17025;
	private boolean ensayoIso17025;
	private String propietario= "";
	private String coeficiente= "";
	
	public ObjetoDTO() {
		
	}
	

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getIndGrupo() {
		return indGrupo;
	}

	public void setIndGrupo(String indGrupo) {
		this.indGrupo = indGrupo;
	}

	public String getPeriodoCalibracion() {
		return periodoCalibracion;
	}

	public void setPeriodoCalibracion(String periodoCalibracion) {
		this.periodoCalibracion = periodoCalibracion;
	}

	public String getCalibradorExterno() {
		return calibradorExterno;
	}

	public void setCalibradorExterno(String calibradorExterno) {
		this.calibradorExterno = calibradorExterno;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getCodigoUbicacion() {
		return codigoUbicacion;
	}

	public void setCodigoUbicacion(String codigoUbicacion) {
		this.codigoUbicacion = codigoUbicacion;
	}

	public Date getFechaUltimaCalibracion() {
		return fechaUltimaCalibracion;
	}

	public void setFechaUltimaCalibracion(Date fechaUltimaCalibracion) {
		this.fechaUltimaCalibracion = fechaUltimaCalibracion;
	}

	public Date getFechaProximaCalibracion() {
		return fechaProximaCalibracion;
	}

	public void setFechaProximaCalibracion(Date fechaProximaCalibracion) {
		this.fechaProximaCalibracion = fechaProximaCalibracion;
	}

	public String getDatosTecnicosEquipo() {
		return datosTecnicosEquipo;
	}

	public void setDatosTecnicosEquipo(String datosTecnicosEquipo) {
		this.datosTecnicosEquipo = datosTecnicosEquipo;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public String getErrorActual() {
		return errorActual;
	}

	public void setErrorActual(String errorActual) {
		this.errorActual = errorActual;
	}

	public String getErrorMaxAdmisibre() {
		return errorMaxAdmisibre;
	}

	public void setErrorMaxAdmisibre(String errorMaxAdmisibre) {
		this.errorMaxAdmisibre = errorMaxAdmisibre;
	}

	public boolean isEquipoCalibrado() {
		return equipoCalibrado;
	}

	public void setEquipoCalibrado(boolean equipoCalibrado) {
		this.equipoCalibrado = equipoCalibrado;
	}

	public boolean isIso17025() {
		return iso17025;
	}

	public void setIso17025(boolean iso17025) {
		this.iso17025 = iso17025;
	}

	public boolean isEnsayoIso17025() {
		return ensayoIso17025;
	}

	public void setEnsayoIso17025(boolean ensayoIso17025) {
		this.ensayoIso17025 = ensayoIso17025;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(String coeficiente) {
		this.coeficiente = coeficiente;
	}

}
