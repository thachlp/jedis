package redis.clients.jedis.commands;

import redis.clients.jedis.args.FlushMode;
import redis.clients.jedis.args.FunctionRestorePolicy;
import redis.clients.jedis.params.FunctionLoadParams;
import redis.clients.jedis.resps.FunctionStats;
import redis.clients.jedis.resps.LibraryInfo;

import java.util.List;

public interface FunctionBinaryCommands {

/**
   * Invoke a function.
   * @param name
   * @param keys
   * @param args
   */
  Object fcall(byte[] name, List<byte[]> keys, List<byte[]> args);

  Object fcallReadonly(byte[] name, List<byte[]> keys, List<byte[]> args);

  /**
   * This command deletes the library called library-name and all functions in it.
   * If the library doesn't exist, the server returns an error.
   * @param libraryName
   * @return OK
   */
  String functionDelete(byte[] libraryName);

  /**
   * Return the serialized payload of loaded libraries. You can restore the
   * serialized payload later with the {@link FunctionBinaryCommands#functionRestore(byte[], FunctionRestorePolicy) FUNCTION RESTORE} command.
   * @return the serialized payload
   */
  byte[] functionDump();

  /**
   * Deletes all the libraries, unless called with the optional mode argument, the
   * 'lazyfree-lazy-user-flush' configuration directive sets the effective behavior.
   * @return OK
   */
  String functionFlush();

  /**
   * Deletes all the libraries, unless called with the optional mode argument, the
   * 'lazyfree-lazy-user-flush' configuration directive sets the effective behavior.
   * @param mode ASYNC: Asynchronously flush the libraries, SYNC: Synchronously flush the libraries.
   * @return OK
   */
  String functionFlush(FlushMode mode);

  /**
   * Kill a function that is currently executing. The command can be used only on functions
   * that did not modify the dataset during their execution.
   * @return OK
   */
  String functionKill();

  /**
   * Return information about the functions and libraries.
   * @return {@link LibraryInfo}
   */
  List<Object> functionListBinary();

  /**
   * Return information about the functions and libraries.
   * @param libraryNamePattern a pattern for matching library names
   * @return {@link LibraryInfo}
   */
  List<Object> functionList(byte[] libraryNamePattern);

  /**
   * Similar to {@link FunctionCommands#functionList() FUNCTION LIST} but include the
   * libraries source implementation in the reply.
   * @see FunctionCommands#functionList()
   * @return {@link LibraryInfo}
   */
  List<Object> functionListWithCodeBinary();

  /**
   * Similar to {@link FunctionBinaryCommands#functionList(byte[]) FUNCTION LIST} but include the
   * libraries source implementation in the reply.
   * @see FunctionBinaryCommands#functionList(byte[])
   * @param libraryNamePattern a pattern for matching library names
   * @return {@link LibraryInfo}
   */
  List<Object> functionListWithCode(byte[] libraryNamePattern);

  /**
   * Load a library to Redis.
   * @param engineName the name of the execution engine for the library
   * @param libraryName the unique name of the library
   * @param functionCode the source code
   * @return OK
   */
  String functionLoad(byte[] engineName, byte[] libraryName, byte[] functionCode);

  /**
   * Load a library to Redis.
   * @param engineName the name of the execution engine for the library
   * @param libraryName the unique name of the library
   * @param params {@link FunctionLoadParams}
   * @param functionCode the source code
   * @return OK
   */
  String functionLoad(byte[] engineName, byte[] libraryName, FunctionLoadParams params, byte[] functionCode);

  /**
   * Restore libraries from the serialized payload. Default policy is APPEND.
   * @param serializedValue the serialized payload
   * @return OK
   */
  String functionRestore(byte[] serializedValue);

  /**
   * Restore libraries from the serialized payload.
   * @param serializedValue the serialized payload
   * @param policy can be {@link FunctionRestorePolicy FLUSH, APPEND or REPLACE}
   * @return OK
   */
  String functionRestore(byte[] serializedValue, FunctionRestorePolicy policy);

  /**
   * Return information about the function that's currently running and information
   * about the available execution engines.
   * @return {@link FunctionStats}
   */
  Object functionStatsBinary();

}