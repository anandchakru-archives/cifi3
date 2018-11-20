package com.anandchakru.app.model.enums;

/**
 * Max-length: 25
 * @author anand
 *
 */
public enum HistoryStatus {
	CODE_PUSH, // Code commit, at this state would trigger appropriate Pipe to start a build
	BUILD_START, // Build in-progress
	BUILT, // Build Success
	BUILD_SKIP, // Build Skipped
	BUILD_FAILED; // Build failure
}