package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 9/18/14.
 */

/**
 * Baseclass that gives some default values for a contract
 */
public class ContractTerms extends BaseRubyModel
{
	private boolean tenantCanAlwaysTerminate = false;
	private boolean ownerCanAlwaysTerminate = false;
	private boolean contractTerminatedByWar = false;
}
