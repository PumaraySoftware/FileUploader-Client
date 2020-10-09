package pojo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import util.XmlLongAdapter;

@XmlRootElement(name = "pum-file")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "PUM_FILE")
public class PumFile implements Serializable {

	@Transient
	@XmlTransient
	private static final long serialVersionUID = -2959135507884205313L;

	@XmlID
	@XmlAttribute(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
	@Column(name = "id")
	@XmlJavaTypeAdapter(XmlLongAdapter.class)
	private Long id;

	@XmlElement(name = "name")
	@Column(name = "NAME")
	private String name;

	@XmlElement(name = "extension")
	@Column(name = "EXTENSION")
	private String extension;

	@XmlElement(name = "size")
	@Column(name = "SIZE")
	private long size;

	@XmlElement(name = "data")
	@Column(name = "DATA")
	private byte[] data;

	protected PumFile() {
	}

	public PumFile(String name, String extension, long size, byte[] data) {
		this.name = name;
		this.extension = extension;
		this.size = size;
		this.data = data;
	}

	public PumFile(File file) throws IOException {
		this.name = file.getName();
		this.extension = name.substring(name.lastIndexOf("."));
		this.size = file.length();
		this.data = getFileData(new FileInputStream(file));
	}

	public PumFile(Path path) throws IOException {
		this.name = path.getFileName().toString();
		this.extension = name.substring(name.lastIndexOf("."));
		this.size = Files.size(path);
		this.data = Files.readAllBytes(path);
	}

	private byte[] getFileData(InputStream inStream) throws IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while (inStream.read(buffer) != -1) {
			out.write(buffer);
		}
		return out.toByteArray();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PumFile [name=" + name + ", extension=" + extension + ", size=" + size + ", data="
				+ Arrays.toString(data) + "]";
	}

}
