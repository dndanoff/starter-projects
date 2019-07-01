var BrowserRouter = ReactRouterDOM.BrowserRouter;
var Route = ReactRouterDOM.Route;


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  
  
  render() {
    return (
    	<p>Hello World</p>
    );
  }
}
  
ReactDOM.render(
	<App />,
	document.getElementById('react-root')
);