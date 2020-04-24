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

package org.apache.druid.spark.v2

import org.apache.druid.spark.utils.SerializableConfiguration
import org.apache.druid.timeline.DataSegment
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.sources.Filter
import org.apache.spark.sql.sources.v2.reader.{InputPartition, InputPartitionReader}
import org.apache.spark.sql.types.StructType

/**
  * Defines a single partition in the dataframe's underlying RDD. This object is generated in the driver and then
  * serialized to the executors where it is responsible for creating the actual ([[InputPartitionReader]]) which
  * does the actual reading.
  *
  * @param location
  * @param schema
  */
class DruidInputPartition(
                           segment: DataSegment,
                           schema: StructType,
                           filters: Array[Filter],
                           columnTypes: Option[Set[String]] = None,
                           useCompactSketches: Boolean = false
                         ) extends InputPartition[InternalRow] {
  // There's probably a better way to do this
  private val session = SparkSession.getActiveSession.get // We're running on the driver, it exists
  private val broadcastConf =
    session.sparkContext.broadcast(
      new SerializableConfiguration(session.sparkContext.hadoopConfiguration))
  private val serializedSegment: String = DruidDataSourceV2.MAPPER.writeValueAsString(segment)

  override def createPartitionReader(): InputPartitionReader[InternalRow] = {
    new DruidInputPartitionReader(serializedSegment, schema, filters, columnTypes, broadcastConf)
  }
}