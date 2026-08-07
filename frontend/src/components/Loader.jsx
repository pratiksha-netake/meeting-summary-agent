import "../styles/global.css";


function Loader({ text = "Loading..." }) {


    return (

        <div className="loader-container">


            <div className="loader-circle"></div>


            <p>
                {text}
            </p>


        </div>

    );


}


export default Loader;