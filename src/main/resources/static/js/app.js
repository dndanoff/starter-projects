var BrowserRouter = ReactRouterDOM.BrowserRouter;
var Route = ReactRouterDOM.Route;


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  
  
  render() {
    return (
    	<BrowserRouter>
			<div class="container">
				<Menu />
				<div className="content">
					<Route exact path="/" component={LandingPage}/>
		            <Route exact path="/employees" render={(props) => <EmployeesPage {...props} pageSize={15}/>}/>
		            <Route path="/employees/:id" component={EmployeeDetailsPage}/>
	            </div>
			</div>
		</BrowserRouter>
    );
  }
}
  
ReactDOM.render(
	<App />,
	document.getElementById('react-root')
);