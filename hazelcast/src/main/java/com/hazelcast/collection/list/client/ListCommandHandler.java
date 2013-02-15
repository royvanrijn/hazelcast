/*
 * Copyright (c) 2008-2010, Hazel Ltd. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.hazelcast.collection.list.client;

import com.hazelcast.client.ClientCommandHandler;
import com.hazelcast.collection.CollectionProxyId;
import com.hazelcast.collection.CollectionProxyType;
import com.hazelcast.collection.CollectionService;
import com.hazelcast.collection.list.ObjectListProxy;
import com.hazelcast.instance.Node;
import com.hazelcast.nio.Protocol;

public abstract class ListCommandHandler extends ClientCommandHandler {

    final CollectionService collectionService;

    public ListCommandHandler(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Override
    public Protocol processCall(Node node, Protocol protocol) {
        String name = protocol.args[0];
        CollectionProxyId id = new CollectionProxyId(ObjectListProxy.COLLECTION_LIST_NAME, name, CollectionProxyType.LIST);
        ObjectListProxy proxy = (ObjectListProxy) collectionService.createDistributedObjectForClient(id);
        return processCall(proxy, protocol);
    }

    protected abstract Protocol processCall(ObjectListProxy proxy, Protocol protocol);
}