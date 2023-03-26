package com.example.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class PaparazziSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun generateSnapshots() {
        val reflections = Reflections("com.example.ui", Scanners.MethodsAnnotated)
        val methods = reflections.getMethodsAnnotatedWith(Preview::class.java)
            .filterNot { java.lang.reflect.Modifier.isPrivate(it.modifiers) }

        val composeProxy = ComposeProxy()
        for (method in methods) {
            val name = "${method.declaringClass.simpleName}_${method.name}"
            try {
                snapshot(name = name) {
                    composeProxy.wrappedInstance(method).generateSnapshot()
                }
            } catch (e: Throwable) {
                throw RuntimeException("Error generating snapshot $name", e)
            }
        }
    }

    private fun snapshot(name: String, composable: @Composable () -> Unit) {
        paparazzi.snapshot(name) {
            Box(modifier = Modifier.background(colors.background)) {
                composable()
            }
        }
    }

    private interface ComposeProxyInterface {

        @SuppressLint("ComposableNaming")
        @Composable
        fun generateSnapshot()
    }

    private class ComposeProxy : ComposeProxyInterface {

        @SuppressLint("ComposableNaming")
        @Composable
        override fun generateSnapshot() {}

        fun wrappedInstance(composableFunction: Method): ComposeProxyInterface {
            val proxyClass = Proxy.getProxyClass(
                ComposeProxy::class.java.classLoader,
                ComposeProxyInterface::class.java
            )

            val invocationHandler = InvocationHandler { _, _, args: Array<out Any>? ->
                composableFunction.invoke(null, *args!!)
            }

            return proxyClass
                .getConstructor(InvocationHandler::class.java)
                .newInstance(invocationHandler) as ComposeProxyInterface
        }
    }
}