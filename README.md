# NewBank
Disrupter bank

## Introduction

NewBank is an idea for a new disrupter bank where customers can interact with their
accounts via a simple command-line interface. The originator of this idea has produced
some basic client-server code as well as a protocol for sending commands and receiving
responses from the NewBank server. The originator of the idea has asked your group to
further develop their base code - specifically, implementing the protocol. However, the
originator is also keen for input and your team can alter or add to the protocol to improve
interaction with the NewBank server as well as to add new services. For example, the
originator is keen for NewBank customers to be able to offer micro-loans to other NewBank
customers in a micro-loan marketplace.

You and a team of developers have taken up this challenge. Your team have access to
the originators basic client-server code as well as the protocol for sending commands and
receiving responses from the NewBank server. The originator has said that your group can
add to, remove and/or alter any part of this basic code as well as the proposed protocol.
Their only requirement is that customers have to interact via a command-line interface
and that any changes/additions to the protocol are fully documented.

## Protocol

A customer enters the command below and sees the messages returned

SHOWMYACCOUNTS
Returns a list of all the customers accounts along with their current balance
e.g. Main: 1000.0

NEWACCOUNT <Name>
e.g. NEWACCOUNT Savings
Returns SUCCESS or FAIL

MOVE <Amount> <From> <To>
e.g. MOVE 100 Main Savings
Returns SUCCESS or FAIL

PAY <Person/Company> <Ammount>
e.g. PAY John 100
Returns SUCCESS or FAIL

## Code

To run the code

1. Run NewBankServer.java in the server folder
2. Run ExampleClient.java in the client folder


