/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.cv.util.distance;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-07-01")
public class WordDist implements org.apache.thrift.TBase<WordDist, WordDist._Fields>, java.io.Serializable, Cloneable, Comparable<WordDist> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WordDist");

  private static final org.apache.thrift.protocol.TField BEST_WORD_FIELD_DESC = new org.apache.thrift.protocol.TField("bestWord", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField BEST_DIST_FIELD_DESC = new org.apache.thrift.protocol.TField("bestDist", org.apache.thrift.protocol.TType.DOUBLE, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new WordDistStandardSchemeFactory());
    schemes.put(TupleScheme.class, new WordDistTupleSchemeFactory());
  }

  public String bestWord; // required
  public double bestDist; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    BEST_WORD((short)1, "bestWord"),
    BEST_DIST((short)2, "bestDist");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // BEST_WORD
          return BEST_WORD;
        case 2: // BEST_DIST
          return BEST_DIST;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __BESTDIST_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BEST_WORD, new org.apache.thrift.meta_data.FieldMetaData("bestWord", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BEST_DIST, new org.apache.thrift.meta_data.FieldMetaData("bestDist", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WordDist.class, metaDataMap);
  }

  public WordDist() {
  }

  public WordDist(
    String bestWord,
    double bestDist)
  {
    this();
    this.bestWord = bestWord;
    this.bestDist = bestDist;
    setBestDistIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WordDist(WordDist other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetBestWord()) {
      this.bestWord = other.bestWord;
    }
    this.bestDist = other.bestDist;
  }

  public WordDist deepCopy() {
    return new WordDist(this);
  }

  @Override
  public void clear() {
    this.bestWord = null;
    setBestDistIsSet(false);
    this.bestDist = 0.0;
  }

  public String getBestWord() {
    return this.bestWord;
  }

  public WordDist setBestWord(String bestWord) {
    this.bestWord = bestWord;
    return this;
  }

  public void unsetBestWord() {
    this.bestWord = null;
  }

  /** Returns true if field bestWord is set (has been assigned a value) and false otherwise */
  public boolean isSetBestWord() {
    return this.bestWord != null;
  }

  public void setBestWordIsSet(boolean value) {
    if (!value) {
      this.bestWord = null;
    }
  }

  public double getBestDist() {
    return this.bestDist;
  }

  public WordDist setBestDist(double bestDist) {
    this.bestDist = bestDist;
    setBestDistIsSet(true);
    return this;
  }

  public void unsetBestDist() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BESTDIST_ISSET_ID);
  }

  /** Returns true if field bestDist is set (has been assigned a value) and false otherwise */
  public boolean isSetBestDist() {
    return EncodingUtils.testBit(__isset_bitfield, __BESTDIST_ISSET_ID);
  }

  public void setBestDistIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BESTDIST_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case BEST_WORD:
      if (value == null) {
        unsetBestWord();
      } else {
        setBestWord((String)value);
      }
      break;

    case BEST_DIST:
      if (value == null) {
        unsetBestDist();
      } else {
        setBestDist((Double)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case BEST_WORD:
      return getBestWord();

    case BEST_DIST:
      return getBestDist();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case BEST_WORD:
      return isSetBestWord();
    case BEST_DIST:
      return isSetBestDist();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WordDist)
      return this.equals((WordDist)that);
    return false;
  }

  public boolean equals(WordDist that) {
    if (that == null)
      return false;

    boolean this_present_bestWord = true && this.isSetBestWord();
    boolean that_present_bestWord = true && that.isSetBestWord();
    if (this_present_bestWord || that_present_bestWord) {
      if (!(this_present_bestWord && that_present_bestWord))
        return false;
      if (!this.bestWord.equals(that.bestWord))
        return false;
    }

    boolean this_present_bestDist = true;
    boolean that_present_bestDist = true;
    if (this_present_bestDist || that_present_bestDist) {
      if (!(this_present_bestDist && that_present_bestDist))
        return false;
      if (this.bestDist != that.bestDist)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_bestWord = true && (isSetBestWord());
    list.add(present_bestWord);
    if (present_bestWord)
      list.add(bestWord);

    boolean present_bestDist = true;
    list.add(present_bestDist);
    if (present_bestDist)
      list.add(bestDist);

    return list.hashCode();
  }

  @Override
  public int compareTo(WordDist other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetBestWord()).compareTo(other.isSetBestWord());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBestWord()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bestWord, other.bestWord);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBestDist()).compareTo(other.isSetBestDist());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBestDist()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bestDist, other.bestDist);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("WordDist(");
    boolean first = true;

    sb.append("bestWord:");
    if (this.bestWord == null) {
      sb.append("null");
    } else {
      sb.append(this.bestWord);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("bestDist:");
    sb.append(this.bestDist);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class WordDistStandardSchemeFactory implements SchemeFactory {
    public WordDistStandardScheme getScheme() {
      return new WordDistStandardScheme();
    }
  }

  private static class WordDistStandardScheme extends StandardScheme<WordDist> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WordDist struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // BEST_WORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.bestWord = iprot.readString();
              struct.setBestWordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BEST_DIST
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.bestDist = iprot.readDouble();
              struct.setBestDistIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, WordDist struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.bestWord != null) {
        oprot.writeFieldBegin(BEST_WORD_FIELD_DESC);
        oprot.writeString(struct.bestWord);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(BEST_DIST_FIELD_DESC);
      oprot.writeDouble(struct.bestDist);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WordDistTupleSchemeFactory implements SchemeFactory {
    public WordDistTupleScheme getScheme() {
      return new WordDistTupleScheme();
    }
  }

  private static class WordDistTupleScheme extends TupleScheme<WordDist> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WordDist struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetBestWord()) {
        optionals.set(0);
      }
      if (struct.isSetBestDist()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetBestWord()) {
        oprot.writeString(struct.bestWord);
      }
      if (struct.isSetBestDist()) {
        oprot.writeDouble(struct.bestDist);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WordDist struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.bestWord = iprot.readString();
        struct.setBestWordIsSet(true);
      }
      if (incoming.get(1)) {
        struct.bestDist = iprot.readDouble();
        struct.setBestDistIsSet(true);
      }
    }
  }

}

