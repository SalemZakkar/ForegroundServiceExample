import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Foreground Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  MethodChannel channel = const MethodChannel("com.app/AppChannel");
  int _counter = 0;
  bool started = false;
  late Timer timer;
  void _incrementCounterBackground() async {
    if (started) {
      await channel.invokeMethod("stop");
      timer.cancel();
      _counter = 0;
      debugPrint("TimerStopped");
      return;
    }
    await channel.invokeMethod("start");
    timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        _counter++;
      });
      debugPrint("Hello From The Foreground");
      debugPrint(_counter.toString());
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          setState(() {
            _incrementCounterBackground();
            started = !started;
          });
        },
        tooltip: 'Increment',
        child:
            (!started ? const Icon(Icons.play_arrow) : const Icon(Icons.pause)),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
