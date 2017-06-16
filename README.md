##scala-lambda-try

A Hello World for doing an AWS lambda in Scala.  Totally just an experiment.

I used this much more sophisticated example as my pattern:
https://github.com/d6y/aws-lambda-scala-slack
#### sbt
The commands are 

`sbt compile`

`sbt assembly`
#### handler
The handler in the Lambda setup is 

`uk.co.littlestickyleaves.hello::handle`
#### input
Takes json in the format 
`{
  "title": "Dr",
  "firstName": "Eloise",
  "lastName": "Harper"
}`

Outputs a formal greeting if possible ("Hello Dr Harper"), 
then a more familiar greeting if either title or lastName are missing 
but firstName is present ("Hello Eloise"). Defaults to "Hello world"