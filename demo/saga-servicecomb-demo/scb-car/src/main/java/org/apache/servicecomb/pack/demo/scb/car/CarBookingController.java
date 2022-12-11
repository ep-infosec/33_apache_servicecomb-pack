/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.pack.demo.scb.car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "car")
@RequestMapping(path = "/")
public class CarBookingController {
  @Autowired
  private CarBookingService service;

  private final AtomicInteger id = new AtomicInteger(0);

  @CrossOrigin
  @GetMapping(path = "/bookings")
  public List<CarBooking> getAll() {
    return new ArrayList<>(service.getAllBookings());
  }

  @PostMapping(path = "/order/{name}/{cars}")
  public CarBooking order(@PathVariable String name, @PathVariable Integer cars) {
    CarBooking booking = new CarBooking();
    booking.setId(id.incrementAndGet());
    booking.setName(name);
    booking.setAmount(cars);
    service.order(booking);
    return booking;
  }

  @DeleteMapping(path = "/bookings")
  void clear() {
    service.clearAllBookings();
    id.set(0);
  }
}
