package krisapps.customdeathmessages.exceptions;

import org.graalvm.compiler.core.CompilationWrapper;

import java.util.UUID;

public class TargetPlayerNullException extends Exception{

    public UUID getTargetUUID() {
        return targetUUID;
    }

    private final UUID targetUUID;

    public TargetPlayerNullException(String message, UUID targetUUID, Throwable rootException){
        super(message.replace("%", targetUUID.toString()), rootException);
        this.targetUUID = targetUUID;
    }

    public void throwDummyException() throws TargetPlayerNullException {
        throw new TargetPlayerNullException("Manually thrown exception.", null, new Exception());
    }



}
