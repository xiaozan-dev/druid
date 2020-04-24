/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.spark.utils

object DruidDataSourceOptionKeys {
  // Metadata Client Configs
  val metadataDbTypeKey: String = "metadataDbType"
  val metadataHostKey: String = "metadataHost"
  val metadataPortKey: String = "metadataPort"
  val metadataConnectUriKey: String = "metadataConnectUri"
  val metadataUserKey: String = "metadataUser"
  val metadataPasswordKey: String = "metadataPassword"
  val metadataDbcpPropertiesKey: String = "metadataDbcpProperties"
  val metadataBaseNameKey: String = "metadataBaseName"

  // Druid Client Configs
  val brokerHostKey: String = "brokerHost"
  val brokerPortKey: String = "brokerKey"

  // Reader Configs
  val useCompactSketchesKey: String = "useCompactSketches"

  // Writer Configs
  val deepStorageTypeKey: String = "deepStorageType"
  val rollUpSegmentsKey: String = "rollUpSegments"
}