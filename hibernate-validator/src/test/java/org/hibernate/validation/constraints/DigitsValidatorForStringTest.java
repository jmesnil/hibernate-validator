// $Id$
/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.validation.constraints;

import javax.validation.constraints.Digits;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

import org.hibernate.validation.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validation.util.annotationfactory.AnnotationFactory;

/**
 * @author Alaa Nassef
 */
public class DigitsValidatorForStringTest {

	private static DigitsValidatorForString constraint;

	@BeforeClass
	public static void init() {

		AnnotationDescriptor<Digits> descriptor = new AnnotationDescriptor( Digits.class );
		descriptor.setValue( "integer", 5 );
		descriptor.setValue( "fraction", 2 );
		descriptor.setValue( "message", "{validator.digits}" );
		Digits p = AnnotationFactory.create( descriptor );

		constraint = new DigitsValidatorForString();
		constraint.initialize( p );
	}

	@Test
	public void testIsValid() {

		assertTrue( constraint.isValid( null, null ) );
		assertTrue( constraint.isValid( "0", null ) );
		assertTrue( constraint.isValid( "500.2", null ) );
		assertTrue( constraint.isValid( "-12456.22", null ) );
		assertTrue( constraint.isValid( "-000000000.22", null ) );
		//should throw number format exception
		assertFalse( constraint.isValid( "", null ) );
		assertFalse( constraint.isValid( "256874.0", null ) );
		assertFalse( constraint.isValid( "12.0001", null ) );
	}
}