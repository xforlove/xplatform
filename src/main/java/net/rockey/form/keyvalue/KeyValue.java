package net.rockey.form.keyvalue;


public interface KeyValue {

	Record findByCode(String code);

	Record findByRef(String ref);

	void save(Record record);

	void removeByCode(String code);

}
