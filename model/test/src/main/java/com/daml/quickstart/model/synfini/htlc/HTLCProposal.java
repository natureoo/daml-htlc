package com.daml.quickstart.model.synfini.htlc;

import com.daml.ledger.javaapi.data.ContractFilter;
import com.daml.ledger.javaapi.data.CreateAndExerciseCommand;
import com.daml.ledger.javaapi.data.CreateCommand;
import com.daml.ledger.javaapi.data.CreatedEvent;
import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.ExerciseCommand;
import com.daml.ledger.javaapi.data.Identifier;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Template;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Timestamp;
import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.Choice;
import com.daml.ledger.javaapi.data.codegen.ContractCompanion;
import com.daml.ledger.javaapi.data.codegen.ContractTypeCompanion;
import com.daml.ledger.javaapi.data.codegen.Created;
import com.daml.ledger.javaapi.data.codegen.Exercised;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.Update;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.daml.finance.interface$.holding.transferable.Transferable;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class HTLCProposal extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("b6728a9adde6f4768cc085a4134d33a6df63ae62284464575d90c9e56d137142", "Synfini.HTLC", "HTLCProposal");

  public static final Choice<HTLCProposal, HTLCProposal_Accept, HTLC.ContractId> CHOICE_HTLCProposal_Accept = 
      Choice.create("HTLCProposal_Accept", value$ -> value$.toValue(), value$ ->
        HTLCProposal_Accept.valueDecoder().decode(value$), value$ ->
        new HTLC.ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<HTLCProposal, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, HTLCProposal> COMPANION = 
      new ContractCompanion.WithoutKey<>("com.daml.quickstart.model.synfini.htlc.HTLCProposal",
        TEMPLATE_ID, ContractId::new, v -> HTLCProposal.templateValueDecoder().decode(v),
        Contract::new, List.of(CHOICE_HTLCProposal_Accept, CHOICE_Archive));

  public final String sender;

  public final String receiver;

  public final Transferable.ContractId holdingCid;

  public final String hash;

  public final Instant unlockTime;

  public HTLCProposal(String sender, String receiver, Transferable.ContractId holdingCid,
      String hash, Instant unlockTime) {
    this.sender = sender;
    this.receiver = receiver;
    this.holdingCid = holdingCid;
    this.hash = hash;
    this.unlockTime = unlockTime;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<HTLCProposal>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<HTLCProposal>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<HTLCProposal>>>(new CreateCommand(HTLCProposal.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseHTLCProposal_Accept} instead */
  @Deprecated
  public Update<Exercised<HTLC.ContractId>> createAndExerciseHTLCProposal_Accept(
      HTLCProposal_Accept arg) {
    return createAnd().exerciseHTLCProposal_Accept(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseHTLCProposal_Accept} instead */
  @Deprecated
  public Update<Exercised<HTLC.ContractId>> createAndExerciseHTLCProposal_Accept(
      AccountKey receiverAccount) {
    return createAndExerciseHTLCProposal_Accept(new HTLCProposal_Accept(receiverAccount));
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<HTLCProposal>>> create(
      String sender, String receiver, Transferable.ContractId holdingCid, String hash,
      Instant unlockTime) {
    return new HTLCProposal(sender, receiver, holdingCid, hash, unlockTime).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, HTLCProposal> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead */
  @Deprecated
  public static HTLCProposal fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<HTLCProposal> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(5);
    fields.add(new DamlRecord.Field("sender", new Party(this.sender)));
    fields.add(new DamlRecord.Field("receiver", new Party(this.receiver)));
    fields.add(new DamlRecord.Field("holdingCid", this.holdingCid.toValue()));
    fields.add(new DamlRecord.Field("hash", new Text(this.hash)));
    fields.add(new DamlRecord.Field("unlockTime", Timestamp.fromInstant(this.unlockTime)));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<HTLCProposal> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(5, recordValue$);
      String sender = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String receiver = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Transferable.ContractId holdingCid =
          new Transferable.ContractId(fields$.get(2).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      String hash = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      Instant unlockTime = PrimitiveValueDecoders.fromTimestamp.decode(fields$.get(4).getValue());
      return new HTLCProposal(sender, receiver, holdingCid, hash, unlockTime);
    } ;
  }

  public static ContractFilter<Contract> contractFilter() {
    return ContractFilter.of(COMPANION);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof HTLCProposal)) {
      return false;
    }
    HTLCProposal other = (HTLCProposal) object;
    return Objects.equals(this.sender, other.sender) &&
        Objects.equals(this.receiver, other.receiver) &&
        Objects.equals(this.holdingCid, other.holdingCid) &&
        Objects.equals(this.hash, other.hash) && Objects.equals(this.unlockTime, other.unlockTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.sender, this.receiver, this.holdingCid, this.hash, this.unlockTime);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.synfini.htlc.HTLCProposal(%s, %s, %s, %s, %s)",
        this.sender, this.receiver, this.holdingCid, this.hash, this.unlockTime);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<HTLCProposal> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, HTLCProposal, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<HTLCProposal> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, HTLCProposal> {
    public Contract(ContractId id, HTLCProposal data, Optional<String> agreementText,
        Set<String> signatories, Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, HTLCProposal> getCompanion() {
      return COMPANION;
    }

    public static Contract fromIdAndRecord(String contractId, DamlRecord record$,
        Optional<String> agreementText, Set<String> signatories, Set<String> observers) {
      return COMPANION.fromIdAndRecord(contractId, record$, agreementText, signatories, observers);
    }

    public static Contract fromCreatedEvent(CreatedEvent event) {
      return COMPANION.fromCreatedEvent(event);
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<HTLC.ContractId>> exerciseHTLCProposal_Accept(
        HTLCProposal_Accept arg) {
      return makeExerciseCmd(CHOICE_HTLCProposal_Accept, arg);
    }

    default Update<Exercised<HTLC.ContractId>> exerciseHTLCProposal_Accept(
        AccountKey receiverAccount) {
      return exerciseHTLCProposal_Accept(new HTLCProposal_Accept(receiverAccount));
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd implements Exercises<CreateAndExerciseCommand> {
    CreateAnd(Template createArguments) {
      super(createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, HTLCProposal, ?> getCompanion(
        ) {
      return COMPANION;
    }
  }
}
